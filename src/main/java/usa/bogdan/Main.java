package usa.bogdan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    List<Message> elementsList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        main.coordinator(bufferedReader);
    }
    public void coordinator(BufferedReader bufferedReader) throws Exception {
        Integer S = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < S; i++) {
            String line = bufferedReader.readLine();
            if ( line.startsWith("C")) {
                forC(line);
            } else if ( line.startsWith("D")) {
                forD(line);
            }
        }
    }
    public void forC(String line) throws Exception {
        String[] elementsOfLine = line.split(" ");
        String givenVersion = elementsOfLine[1];
        String givenSubversion = elementsOfLine[2];
        String givenOrder = elementsOfLine[3];
        Date givenDate = dateFormat.parse(elementsOfLine[4]);
        int givenWaitingTime = Integer.parseInt(elementsOfLine[5]);
        elementsList.add(new Message(givenVersion,
                givenSubversion,
                givenOrder,
                givenDate,
                givenWaitingTime));
    }
    public void forD(String line) throws Exception {
        List<Integer> waitingList = new ArrayList<>();
        String[] elementsOfLine = line.split(" ");
        String version = elementsOfLine[1];
        String subversion = elementsOfLine[2];
        String order = elementsOfLine[3];
        String date = elementsOfLine[4];

        String[] datesArray = date.split("-");
        Date firstDate = dateFormat.parse(datesArray[0]);
        Date finishingDate = datesArray.length > 1 ? dateFormat.parse(datesArray[1]) : firstDate;

        for (Message message: elementsList) {
            if (matching(message, version, subversion, order) &&
            !message.date.before(firstDate) && !message.date.after(finishingDate)) {
                waitingList.add(message.waitingTime);
            }
        }
        if ( waitingList.isEmpty() ) {
            System.out.println("-");
        } else {
            int avg = waitingList.stream().mapToInt(Integer::intValue).sum() / waitingList.size();
            System.out.println(avg);
        }
    }
    public boolean matching(Message message,
                            String givenVersion,
                            String givenSubversion,
                            String givenOrder) {
        boolean isVersionCoincidence = givenVersion.equals("*") || message.version.startsWith(givenVersion);
        boolean isSubversionCoincidence = givenSubversion.equals("*") || message.subversion.startsWith(givenSubversion);
        boolean isOrderCoincidence = message.order.equals(givenOrder);
        return isVersionCoincidence && isSubversionCoincidence && isOrderCoincidence;
    }
}
