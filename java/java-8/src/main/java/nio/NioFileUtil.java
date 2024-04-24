package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioFileUtil {

    /**
     * 使用【非直接缓冲区】，如果文件不存在，则会创建。
     */
    public static void copyFile(String src, String dest, boolean append) throws IOException {
        File srcFile = new File(src);
        if(!srcFile.exists()){
            System.err.println("src path doesn't exist");
            return;
        }

        /*
         * 构建FileChannel的方式有：
         * 1. FileInputStream FileChannel可读
         * 2. FileOutPutStream FileChannel可写
         * 3. RandomAccessFile
         * 4. FileChannel.open(Path, Mode)
         */
        try(FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dest, append);
            FileChannel fic = fis.getChannel();
            FileChannel foc = fos.getChannel()
        ){
            // 堆空间。
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 初始为写模式。
            while(fic.read(byteBuffer) != -1){
                // 切换为读模式
                byteBuffer.flip();
                foc.write(byteBuffer);
                // clear把position，limit等置为初始值，即标记为原数据为脏数据。
                byteBuffer.clear();
            }
        }
    }

    /**
     * 使用【直接缓冲区】，
     */
    public static void copyFileWithDirectByteBuffer(String src, String dest, boolean append) throws IOException{
        Path srcPath = Paths.get(src);
        if (!srcPath.toFile().exists()){
            System.err.println("src path doesn't exist");
            return;
        }

        /*
         * Channel.open(), 务必保证文件是【存在的】，否则会抛异常FileNotFoundException 或者必须加上StandardOpenOption.CREATE
         */
        Path destPath = Paths.get(dest);
        try(FileChannel inChannel = FileChannel.open(srcPath, StandardOpenOption.READ);
            // create: 无则创建
            // write: 可写
            // append: 追加写
            FileChannel outChannel = append ?
                    FileChannel.open(destPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)
                    : FileChannel.open(destPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)
        ){
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            while(inChannel.read(byteBuffer) != -1){
                // 切换为读模式
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                // 清空缓冲区，成为写模式
                byteBuffer.clear();
            }
        }
    }

    /**
     * 通过mmap的方式来拷贝，不支持append。
     */
    public static void copyByMMap(String src, String dest, int mb) {
        Path srcPath = Paths.get(src);
        if(!srcPath.toFile().exists()){
            System.err.println("src path doesn't exist");
            return;
        }


        Path destPath = Paths.get(dest);
        try(FileChannel inChannel = FileChannel.open(srcPath, StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(destPath, StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE)
        ){
            // 一次map，一旦遇到大文件，则有可能将堆内存消耗殆尽，OOM，map本身使用的是堆外空间，但byte[] bs使用的是堆空间。
            //MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            //MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //byte[] bs = new byte[inChannel.size()];
            //inBuffer.get(bs);
            //outBuffer.put(bs);

            // 于是分多次map
            int size = 1024 * 1024 * mb;
            long offset = 0;
            byte[] bs = new byte[size];

            while(offset <= inChannel.size()){
                long actualSize = Math.min(size, inChannel.size() - offset); // 每次实际的Size

                MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, offset, actualSize);
                MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, offset, actualSize);
                inBuffer.get(bs, 0, (int) actualSize);
                outBuffer.put(bs, 0, (int) actualSize);

                offset  += size;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyByTransferTo(String src, String dest){
        try(FileChannel inChannel = FileChannel.open(Paths.get(src), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get(dest), StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE)
        ){
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
