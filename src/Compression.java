public class Compression {

    public static String compressSequence(int[] sequence) {
        StringBuilder result = new StringBuilder();

        // Encode the first value
        result.append(String.format("%8s", Integer.toBinaryString(sequence[0])).replace(' ', '0'));

        int i = 1;
        while (i < sequence.length) {
            int diff = sequence[i] - sequence[i - 1];
            if (diff == 0) {
                // Count repetitions
                int count = 0;
                while (i < sequence.length && sequence[i] == sequence[i - 1] && count < 7) {
                    count++;
                    i++;
                }
                result.append("01").append(String.format("%3s", Integer.toBinaryString(count)).replace(' ', '0'));
            } else {
                result.append(encodeDifference(diff));
                i++;
            }
        }

        // Add the ending code
        result.append("11");
        return result.toString();
    }

    private static String encodeDifference(int diff) {
        if (-2 <= diff && diff <= 2) {
            return "00" + String.format("%2s", Integer.toBinaryString(diff + 2)).replace(' ', '0');
        } else if (-6 <= diff && diff <= 6) {
            return "01" + String.format("%3s", Integer.toBinaryString(diff + 6)).replace(' ', '0');
        } else if (-14 <= diff && diff <= 14) {
            return "10" + String.format("%4s", Integer.toBinaryString(diff + 14)).replace(' ', '0');
        } else if (-30 <= diff && diff <= 30) {
            return "11" + String.format("%5s", Integer.toBinaryString(diff + 30)).replace(' ', '0');
        } else {
            // Absolute coding for differences greater than 30
            String signBit = diff >= 0 ? "0" : "1";
            String valueBits = String.format("%8s", Integer.toBinaryString(Math.abs(diff))).replace(' ', '0');
            return "10" + signBit + valueBits;
        }
    }

    public static int[] decompressSequence(String encoded) {
        int index = 0;
        int firstValue = Integer.parseInt(encoded.substring(index, index + 8), 2);
        int[] sequence = new int[encoded.length()];
        sequence[0] = firstValue;
        index += 8;
        int seqIndex = 1;

        while (index < encoded.length()) {
            String code = encoded.substring(index, index + 2);
            index += 2;
            if (code.equals("11")) {
                break;
            } else if (code.equals("01")) {
                int count = Integer.parseInt(encoded.substring(index, index + 3), 2);
                index += 3;
                for (int j = 0; j <= count; j++) {
                    sequence[seqIndex] = sequence[seqIndex - 1];
                    seqIndex++;
                }
            } else if (code.equals("10")) {
                char sign = encoded.charAt(index);
                int value = Integer.parseInt(encoded.substring(index + 1, index + 9), 2);
                int diff = sign == '0' ? value : -value;
                index += 9;
                sequence[seqIndex] = sequence[seqIndex - 1] + diff;
                seqIndex++;
            } else {
                int bitsLength = switch (code) {
                    case "00" -> 2;
                    case "01" -> 3;
                    case "10" -> 4;
                    case "11" -> 5;
                    default -> throw new IllegalArgumentException("Unknown code type");
                };
                int diff = decodeDifference(encoded.substring(index, index + bitsLength), code);
                index += bitsLength;
                sequence[seqIndex] = sequence[seqIndex - 1] + diff;
                seqIndex++;
            }
        }

        // Trim the sequence array to the actual length
        int[] trimmedSequence = new int[seqIndex];
        System.arraycopy(sequence, 0, trimmedSequence, 0, seqIndex);
        return trimmedSequence;
    }

    private static int decodeDifference(String bits, String codeType) {
        switch (codeType) {
            case "00":
                return Integer.parseInt(bits, 2) - 2;
            case "01":
                return Integer.parseInt(bits, 2) - 6;
            case "10":
                return Integer.parseInt(bits, 2) - 14;
            case "11":
                return Integer.parseInt(bits, 2) - 30;
            default:
                throw new IllegalArgumentException("Unknown code type");
        }
    }

    public static void main(String[] args) {
        int[] sequence = {55, 53, 53, 53, 53, 53, 10, 10, 11, 11, 11, 11};
        String encoded = compressSequence(sequence);
        System.out.println("Encoded sequence: " + encoded);
        int[] decoded = decompressSequence(encoded);
        System.out.print("Decoded sequence: ");
        for (int value : decoded) {
            System.out.print(value + " ");
        }
    }
}
