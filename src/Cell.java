import java.util.ArrayList;

public class Cell {
    Status status;
    ArrayList<Cell> near;

    public Cell() {
        status = Status.NONE;
        near = new ArrayList<>();
    }

    void addNear(Cell cell) {
        near.add(cell);
    }

    void step1() {
        int around = countNearCell();
        status = status.step1(around);
    }

    int countNearCell() {
        int count = 0;
        for (Cell cell : near)
            if (cell.status.isCell())
                count++;
        return count;
    }

    void step2() {
        status = status.step2();
    }

    void turn() {

        //переворачивает 1 клетку
        /*status = Status.LIFE; */

        //переворачивает 8 соседних клеток
        for(Cell cell : near){
            //cell.status = Status.LIFE;
            cell.status = cell.status.isCell() ? Status.NONE : Status.LIFE;
        }
    }
}
