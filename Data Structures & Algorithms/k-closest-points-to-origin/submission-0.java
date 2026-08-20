class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int rows = points.length;
        int col = points[0].length;
        Comparator<Point> reverseDist = (a,b) -> Double.compare(b.distance, a.distance);
        //max heap
        PriorityQueue<Point> pq = new PriorityQueue<>(reverseDist);
        //iterate through matrix, calculating dist and adding
            //removing when heap size exceeds k
        for(int i = 0; i < rows; i++){
            int x = points[i][0];
            int y = points[i][1];
            pq.add(new Point(x, y));
        }
        //ensures only k smallest distance remain in pq
        while(pq.size()>k){
            Point p = pq.peek();
            System.out.println("Top item is " + p.x + ", " + p.y);
            pq.poll();
        }
        //fill int[][]
        int[][] sol = new int[k][2];
        for(int i = 0; i < k; i++){
            Point p = pq.poll();
            sol[i][0] = p.x;
            sol[i][1] = p.y;
        }
        return sol;
    }
}

public class Point implements Comparable<Point>{
    public int x;
    public int y;
    public double distance;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        double x1 = (double) x;
        double y1 = (double) y;
        distance = Math.sqrt(x1 * x1 + y1 * y1);
    }

    //natural ordering is now defined as lowest dist first
    public int compareTo(Point other){
        return Double.compare(this.distance, other.distance);
    }
}
