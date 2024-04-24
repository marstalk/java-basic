package dp;

/**
 * 给你两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为 子序列 的最短字符串。如果答案不止一个，则可以返回满足条件的 任意一个 答案。
 * 如果从字符串 t 中删除一些字符（也可能不删除），可以得到字符串 s ，那么 s 就是 t 的一个子序列。
 * <p>
 * 输入：str1 = "abac", str2 = "cab"
 * 输出："cabac"
 * 解释：
 * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
 * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
 * 最终我们给出的答案是满足上述属性的最短字符串。
 * <p>
 * 输入：str1 = "aaaaaaaa", str2 = "aaaaaaaa"
 * 输出："aaaaaaaa"
 */
public class AAShortestCommonSuperSequence3BottomsUp2 {
    public static void main(String[] args) {
        AAShortestCommonSuperSequence3BottomsUp2 hShortestCommonSuperSequence1 = new AAShortestCommonSuperSequence3BottomsUp2();
        System.out.println(hShortestCommonSuperSequence1.scss("abac", "cab")); // 5
        System.out.println(hShortestCommonSuperSequence1.scss("aaaaaaaa", "aaaaaaaa")); // 8
        System.out.println(hShortestCommonSuperSequence1.scss("atdznrqfwlfbcqkezrltzyeqvqemikzgghxkzenhtapwrmrovwtpzzsyiwongllqmvptwammerobtgmkpowndejvbuwbporfyroknrjoekdgqqlgzxiisweeegxajqlradgcciavbpgqjzwtdetmtallzyukdztoxysggrqkliixnagwzmassthjecvfzmyonglocmvjnxkcwqqvgrzpsswnigjthtkuawirecfuzrbifgwolpnhcapzxwmfhvpfmqapdxgmddsdlhteugqoyepbztspgojbrmpjmwmhnldunskpvwprzrudbmtwdvgyghgprqcdgqjjbyfsujnnssfqvjhnvcotynidziswpzhkdszbblustoxwtlhkowpatbypvkmajumsxqqunlxxvfezayrolwezfzfyzmmneepwshpemynwzyunsxgjflnqmfghsvwpknqhclhrlmnrljwabwpxomwhuhffpfinhnairblcayygghzqmotwrywqayvvgohmujneqlzurxcpnwdipldofyvfdurbsoxdurlofkqnrjomszjimrxbqzyazakkizojwkuzcacnbdifesoiesmkbyffcxhqgqyhwyubtsrqarqagogrnaxuzyggknksrfdrmnoxrctntngdxxechxrsbyhtlbmzgmcqopyixdomhnmvnsafphpkdgndcscbwyhueytaeodlhlzczmpqqmnilliydwtxtpedbncvsqauopbvygqdtcwehffagxmyoalogetacehnbfxlqhklvxfzmrjqofaesvuzfczeuqegwpcmahhpzodsmpvrvkzxxtsdsxwixiraphjlqawxinlwfspdlscdswtgjpoiixbvmpzilxrnpdvigpccnngxmlzoentslzyjjpkxemyiemoluhqifyonbnizcjrlmuylezdkkztcphlmwhnkdguhelqzjgvjtrzofmtpuhifoqnokonhqtzxmimp",
                "xjtuwbmvsdeogmnzorndhmjoqnqjnhmfueifqwleggctttilmfokpgotfykyzdhfafiervrsyuiseumzmymtvsdsowmovagekhevyqhifwevpepgmyhnagjtsciaecswebcuvxoavfgejqrxuvnhvkmolclecqsnsrjmxyokbkesaugbydfsupuqanetgunlqmundxvduqmzidatemaqmzzzfjpgmhyoktbdgpgbmjkhmfjtsxjqbfspedhzrxavhngtnuykpapwluameeqlutkyzyeffmqdsjyklmrxtioawcrvmsthbebdqqrpphncthosljfaeidboyekxezqtzlizqcvvxehrcskstshupglzgmbretpyehtavxegmbtznhpbczdjlzibnouxlxkeiedzoohoxhnhzqqaxdwetyudhyqvdhrggrszqeqkqqnunxqyyagyoptfkolieayokryidtctemtesuhbzczzvhlbbhnufjjocporuzuevofbuevuxhgexmckifntngaohfwqdakyobcooubdvypxjjxeugzdmapyamuwqtnqspsznyszhwqdqjxsmhdlkwkvlkdbjngvdmhvbllqqlcemkqxxdlldcfthjdqkyjrrjqqqpnmmelrwhtyugieuppqqtwychtpjmloxsckhzyitomjzypisxzztdwxhddvtvpleqdwamfnhhkszsfgfcdvakyqmmusdvihobdktesudmgmuaoovskvcapucntotdqxkrovzrtrrfvoczkfexwxujizcfiqflpbuuoyfuoovypstrtrxjuuecpjimbutnvqtiqvesaxrvzyxcwslttrgknbdcvvtkfqfzwudspeposxrfkkeqmdvlpazzjnywxjyaquirqpinaennweuobqvxnomuejansapnsrqivcateqngychblywxtdwntancarldwnloqyywrxrganyehkglbdeyshpodpmdchbcc")); // 8
    }

    /**
     * dp(i, j)表示s1[0,i),s2[0,j)这个子问题的解，即他们的最短公共超序列。
     */
    public String scss(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        Info[][] dp = new Info[m + 1][n + 1];

        // init
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = new Info(i + 1, new StringBuilder(str1.subSequence(0, i)));
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = new Info(j + 1, new StringBuilder(str2.subSequence(0, j)));
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                Info info;
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    Info preInfo = dp[i - 1][j - 1];
                    String preSequence = preInfo.superSequence.toString();
                    info = new Info(preInfo.min + 1, new StringBuilder(preSequence).append(str1.charAt(i - 1)));
                } else {
                    // str1[i-1]没有出现在超序列中
                    Info a = dp[i - 1][j];
                    String preSeqA = a.superSequence.toString();
                    Info infoA = new Info(a.min + 1, new StringBuilder(preSeqA).append(str1.charAt(i - 1)));

                    // str2[j-1]没有出现在超序列中
                    Info b = dp[i][j - 1];
                    String preSeqB = b.superSequence.toString();
                    Info infoB = new Info(b.min + 1, new StringBuilder(preSeqB).append(str2.charAt(j - 1)));
                    info = infoA.min < infoB.min ? infoA : infoB;

                    // str1[i-1]和str2[j-1]都没有出现在超序列中
                    Info c = dp[i - 1][j - 1];
                    String preSeqC = c.superSequence.toString();
                    Info infoC = new Info(c.min + 2, new StringBuilder(preSeqC).append(str1.charAt(i - 1)).append(str2.charAt(j - 1)));

                    // 选择最小的。
                    info = info.min < infoC.min ? info : infoC;
                }

                dp[i][j] = info;
            }
        }

        return dp[m][n].superSequence.toString();
    }

    private static class Info {
        int min;
        StringBuilder superSequence = new StringBuilder();

        public Info(int min, StringBuilder s) {
            this.min = min;
            this.superSequence = s;
        }

        @Override
        public String toString() {
            return superSequence.toString();
        }
    }
}
