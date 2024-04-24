package ik;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class IkDemo {
    public static void main(String[] args) throws IOException {
        String str = "中华人民共和国合同法";
        StringReader reader = new StringReader(str);
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        TokenStream tokenStream = ikAnalyzer.tokenStream(str, reader);
        CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);
        while(tokenStream.incrementToken()){
            System.out.print(termAttribute.toString() + "|");
        }
        System.out.println();
        ikAnalyzer.close();
    }
}
