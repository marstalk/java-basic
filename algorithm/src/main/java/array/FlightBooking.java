package array;

public class FlightBooking {
    public static void main(String[] args) {
        FlightBooking flightBooking = new FlightBooking();
        int[] rtn = flightBooking.corpFlightBookings(new int[][]{{1,2,10},{2,3,20},{2,5,25}}, 5);
        for (int i = 0; i < rtn.length; i++) {
            System.out.println(rtn[i]);
        }
    }

    

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];

        for (int i = 0; i < bookings.length; i++) {
            int left = bookings[i][0] -1;
            int right = bookings[i][1] - 1;
            int val = bookings[i][2];

            diff[left] += val;
            if(right < n -1){
                diff[right + 1] -= val;
            }
        }

        int[] rtn = new int[n];
        rtn[0] = diff[0];
        for (int i = 1; i < rtn.length; i++) {
            rtn[i] = diff[i] + rtn[i-1];
        }
        return rtn;
    }
    
}
