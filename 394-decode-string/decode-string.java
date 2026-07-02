class Solution {
    private int pos = 0;

    public String decodeString(String s) {
        pos = 0;
        return decode(s);
    }

    private String decode(String s) {
        StringBuilder box = new StringBuilder(); 
        int num = 0;

        while (pos < s.length()) {
            char c = s.charAt(pos);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0'); 
                pos++;
            } else if (c == '[') {
                pos++;
                String innerBox = decode(s);
                for (int i = 0; i < num; i++) {
                    box.append(innerBox); 
                }
                num = 0;
            } else if (c == ']') {
                pos++;
                return box.toString(); 
            } else {
                box.append(c); 
                pos++;
            }
        }
        return box.toString();
    }
}