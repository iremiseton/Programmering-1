package EU3;

import java.util.Random;

public class Chessboard{

    public static class Field{   
        private char row;
        private byte column;
        private Chesspiece piece = null;
        private boolean marked = false;
        
        public Field (char row, byte column) {
            this.row = row;
            this.column = column;
        }
        
        public void put (Chesspiece piece) {
            this.piece = piece;
        }

        public Chesspiece take () {
            this.piece = null;
            return null;
        }
        
        public void mark () {
            this.marked = true;
        }

        public void unmark () {
            this.marked = false;
        } 
        
        public String toString () {
            String s = (marked)? "xx" : "--";
            return (piece == null)? s : piece.toString ();
        }
    }

    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int FIRST_ROW = 'a';
    public static final int FIRST_COLUMN = 1;
    private Field[][] fields;
    
    public Chessboard() {

        fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char row = 0;
        byte column = 0;
        for (int r = 0;r < NUMBER_OF_ROWS; r++){
            row = (char) (FIRST_ROW + r);
            column = FIRST_COLUMN;
            for (int c = 0; c < NUMBER_OF_COLUMNS; c++){
                fields[r][c] = new Field (row, column);
                column++;
            }
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(" ");
        for (int j = 0; j < 8; j++) {
            s.append("  ");
            s.append((char) ('1' + j)); 
        }
            

        s.append("\n");

        for (int i = 0; i < 8; i++) {
            s.append((char)('a' + i) + " ");
            for (int j = 0; j < 8; j++) {
                s.append(" " + this.fields[i][j]);
            }
            s.append("\n");
        }

        return s.toString();
    }
    
    public boolean isValidField (char row, byte column){
        return row >= 97 && row <= 104 && column <= 8 && column >= 1;
    }

    public abstract class Chesspiece {
        private char color;// w -white, b -black
        private char name;// K -King, Q -Queen, R -Rook, B -Bishop, N -Knight, P –Pawnprotected 
        char row = 0;
        protected byte column = -1;

        protected Chesspiece (char color, char name){
            this.color = color;
            this.name = name;

            this.column = (byte) (1 + new Random().nextInt(7));
            this.row = (char) ('a' + new Random().nextInt(8));
        }

        public String toString (){return "" + color + name;}
        public boolean isOnBoard (){return Chessboard.this.isValidField(row, column);}

        public void moveTo (char row, byte column) throws NotValidFieldException {
            if (!Chessboard.this.isValidField (row, column)) {
                throw new NotValidFieldException ("bad field: " + row + column);
            }
  
            this.row = row;
            this.column = column;
            int r = row -FIRST_ROW;
            int c = column -FIRST_COLUMN;
            Chessboard.this.fields[r][c].put(this);
        }

        public void moveOut () {
            if (isOnBoard()) {
                Chessboard.this.fields[row - FIRST_ROW][column - FIRST_COLUMN].take();
            }
        }
        public abstract void markReachableFields ();
        public abstract void unmarkReachableFields ();
    }

    public class Pawn extends Chesspiece {
        public Pawn (char color, char name){
            super (color, name);
        }

        public void markReachableFields (){
            byte col = (byte) (column + 1);
            if (Chessboard.this.isValidField (row, col)){
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].mark();
            }
        }

        public void unmarkReachableFields (){
            byte col = (byte) (column + 1);
            if (Chessboard.this.isValidField (row, col)){
                int r = row -FIRST_ROW;
                int c = col -FIRST_COLUMN;
                Chessboard.this.fields[r][c].unmark ();
            }
        }
    }

    public class Rook extends Chesspiece{
        public Rook (char color, char name) {
            super(color, name);
        }

        public void markReachableFields() {


            for (int i = 1; i <= 8; i++) {
                if (Chessboard.this.isValidField(row, (byte) (i))) {
                    Chessboard.this.fields[row - FIRST_ROW][i - FIRST_COLUMN].mark();
                }
            }

            for (int j = 0; j < 8; j++) {
                if (Chessboard.this.isValidField((char) (j + FIRST_ROW), (byte) (column))) {
                    Chessboard.this.fields[j][column - FIRST_COLUMN].mark();
                }
            }
        }

        public void unmarkReachableFields() {

            for (int i = 1; i <= 8; i++) {
                if (Chessboard.this.isValidField(row, (byte) (i))) {
                    Chessboard.this.fields[row - FIRST_ROW][i - FIRST_COLUMN].unmark();
                }
            }

            for (int j = 0; j < 8; j++) {
                if (Chessboard.this.isValidField((char) (j + FIRST_ROW), (byte) (column))) {
                    Chessboard.this.fields[j][column - FIRST_COLUMN].unmark();
                }
            }
        }
    }

    public class Knight extends Chesspiece {

        public Knight (char color, char name) {
            super(color, name);
        }

        public void unmarkReachableFields() {
            
            /**
             *     höger - ner
             */
            byte c = (byte) (column + 2);
            char r = (char) (row + 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }
        
            /**
             *      höger - ner
             */
            c = (byte) (column + 2);
            r = (char) (row - 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }

            /**
             *      vänster upp
             */
            c = (byte) (column - 2);
            r = (char) (row + 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }

            /**
             *      vänster ner
             */
            c = (byte) (column - 2);
            r = (char) (row - 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }

            /**
             *      upp höger
             */
            c = (byte) (column + 1);
            r = (char) (row + 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }

            /**
             *      upp vänster
             */
            c = (byte) (column + 1);
            r = (char) (row - 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }

            /**
             *      ner höger
             */
            c = (byte) (column - 1);
            r = (char) (row + 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }

            /**
             *      ner vänster
             */
            c = (byte) (column - 1);
            r = (char) (row - 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].unmark(); }
            
        }

        public void markReachableFields() {
            /**
             *     höger - ner
             */
            byte c = (byte) (column + 2);
            char r = (char) (row + 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }
        
            /**
             *      höger - ner
             */
            c = (byte) (column + 2);
            r = (char) (row - 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }

            /**
             *      vänster upp
             */
            c = (byte) (column - 2);
            r = (char) (row + 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }

            /**
             *      vänster ner
             */
            c = (byte) (column - 2);
            r = (char) (row - 1);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }

            /**
             *      upp höger
             */
            c = (byte) (column + 1);
            r = (char) (row + 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }

            /**
             *      upp vänster
             */
            c = (byte) (column + 1);
            r = (char) (row - 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }

            /**
             *      ner höger
             */
            c = (byte) (column - 1);
            r = (char) (row + 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }

            /**
             *      ner vänster
             */
            c = (byte) (column - 1);
            r = (char) (row - 2);
            if (Chessboard.this.isValidField(r, c)) { Chessboard.this.fields[r - FIRST_ROW][c - FIRST_COLUMN].mark(); }
        
        }


    }
    public class Bishop extends Chesspiece {
        
        public Bishop (char color, char name) {
            super(color, name);
        }

        public void unmarkReachableFields() {
            // diagonalt mot höger ner
            int i = 1;
            while (Chessboard.this.isValidField((char) (row + i), (byte) (column + i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column + i - FIRST_COLUMN].unmark();
                i++;
            }

            // diagonalt mot vänster ner
            i = 1;
            while (Chessboard.this.isValidField((char) (row + i), (byte) (column - i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column - i - FIRST_COLUMN].unmark();
                i++;
            }
            
            // diagonalt mot vänster upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column - i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column - i - FIRST_COLUMN].unmark();
                i++;
            }

            // diagonalt mot höger upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column + i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column + i - FIRST_COLUMN].unmark();
                i++;
            }
        }

        public void markReachableFields() {
            

            /*

            public boolean isValidField (char row, byte column){
                return row >= 97 && row <= 104 && column <= 8 && column >= 1;
            }
            */

            // diagonalt mot höger ner
            int i = 1;
            while (Chessboard.this.isValidField((char) (row + i), (byte) (column + i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column + i - FIRST_COLUMN].mark();
                i++;
            }

            // diagonalt mot vänster ner
            i = 1;
            while (Chessboard.this.isValidField((char) (row + i), (byte) (column - i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column - i - FIRST_COLUMN].mark();
                i++;
            }
            
            // diagonalt mot vänster upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column - i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column - i - FIRST_COLUMN].mark();
                i++;
            }

            // diagonalt mot höger upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column + i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column + i - FIRST_COLUMN].mark();
                i++;
            }
        }
    }

    public class Queen extends Chesspiece {
        public Queen (char color, char name) {
            super(color, name);
        }

        public void markReachableFields() {
            
            int i = 1;

            while (Chessboard.this.isValidField((char) (row + i), (byte) (column + i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column + i - FIRST_COLUMN].mark();
                i++;
            }

            // diagonalt mot vänster ner
            i = 1;
            while (Chessboard.this.isValidField((char) (row + i), (byte) (column - i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column - i - FIRST_COLUMN].mark();
                i++;
            }
            
            // diagonalt mot vänster upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column - i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column - i - FIRST_COLUMN].mark();
                i++;
            }

            // diagonalt mot höger upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column + i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column + i - FIRST_COLUMN].mark();
                i++;
            }

            for (i = 1; i <= 8; i++) {
                if (Chessboard.this.isValidField(row, (byte) (i))) {
                    Chessboard.this.fields[row - FIRST_ROW][i - FIRST_COLUMN].mark();
                }
            }

            for (int j = 0; j < 8; j++) {
                if (Chessboard.this.isValidField((char) (j + FIRST_ROW), (byte) (column))) {
                    Chessboard.this.fields[j][column - FIRST_COLUMN].mark();
                }
            }
        }

        public void unmarkReachableFields() {
            int i = 1;

            while (Chessboard.this.isValidField((char) (row + i), (byte) (column + i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column + i - FIRST_COLUMN].unmark();
                i++;
            }

            // diagonalt mot vänster ner
            i = 1;
            while (Chessboard.this.isValidField((char) (row + i), (byte) (column - i))) {
                Chessboard.this.fields[(row + i) - FIRST_ROW][column - i - FIRST_COLUMN].unmark();
                i++;
            }
            
            // diagonalt mot vänster upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column - i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column - i - FIRST_COLUMN].unmark();
                i++;
            }

            // diagonalt mot höger upp
            i = 1;
            while (Chessboard.this.isValidField((char) (row - i), (byte) (column + i))) {
                Chessboard.this.fields[(row - i) - FIRST_ROW][column + i - FIRST_COLUMN].unmark();
                i++;
            }

            for (i = 1; i <= 8; i++) {
                if (Chessboard.this.isValidField(row, (byte) (i))) {
                    Chessboard.this.fields[row - FIRST_ROW][i - FIRST_COLUMN].unmark();
                }
            }

            for (int j = 0; j < 8; j++) {
                if (Chessboard.this.isValidField((char) (j + FIRST_ROW), (byte) (column))) {
                    Chessboard.this.fields[j][column - FIRST_COLUMN].unmark();
                }
            }
        }

    }
    public class King extends Chesspiece{
        public King (char color, char name) {
            super(color, name);
        }

        public void unmarkReachableFields() {

            //NER 
            //isValidField kontrollerar ifall att platsen är inom spelplan. Om true, markera.
            if (Chessboard.this.isValidField((char) (row + 1), column)) {
                Chessboard.this.fields[(row + 1) - FIRST_ROW][column - FIRST_COLUMN].unmark();
            }

            //UPP
            if (Chessboard.this.isValidField((char) (row - 1), column)) {
                Chessboard.this.fields[(row - 1) - FIRST_ROW][column - FIRST_COLUMN].unmark();
            }
            
             //HÖGER
             // column är av typen byte. Vid addition av 1 vill den konvertera till int, därav konverteras summan till byte. 
            if (Chessboard.this.isValidField(row, (byte) (column + 1))) {
                Chessboard.this.fields[row - FIRST_ROW][(column + 1) - FIRST_COLUMN].unmark();
            }

             //VÄNSTER
            if (Chessboard.this.isValidField(row, (byte) (column - 1))) {
                Chessboard.this.fields[row - FIRST_ROW][(column - 1) - FIRST_COLUMN].unmark();
            }

            //Diagonal upp höger.
            if (Chessboard.this.isValidField((char) (row - 1), (byte) (column + 1))) {
                Chessboard.this.fields[(row - 1) - FIRST_ROW][(column + 1) - FIRST_COLUMN].unmark();
            }

            //Diagonal upp vänster.
            if (Chessboard.this.isValidField((char) (row - 1), (byte) (column - 1))) {
                Chessboard.this.fields[(row - 1) - FIRST_ROW][(column - 1) - FIRST_COLUMN].unmark();
            }

            //Diagonal ner höger.
            if (Chessboard.this.isValidField((char) (row + 1), (byte) (column + 1))) {
                Chessboard.this.fields[(row + 1) - FIRST_ROW][(column + 1) - FIRST_COLUMN].unmark();
            }

            //Diagonal ner vänster.
            if (Chessboard.this.isValidField((char) (row + 1), (byte) (column - 1))) {
                Chessboard.this.fields[(row + 1) - FIRST_ROW][(column - 1) - FIRST_COLUMN].unmark();
            }

        }

        public void markReachableFields() {

            //NER 
            //isValidField kontrollerar ifall att platsen är inom spelplan. Om true, markera.
            if (Chessboard.this.isValidField((char) (row + 1), column)) {
                // Här markeras rutorna. row + 1 => går ner | column + 1 => hö
                Chessboard.this.fields[(row + 1) - FIRST_ROW][column - FIRST_COLUMN].mark();
            }

            //UPP
            if (Chessboard.this.isValidField((char) (row - 1), column)) {
                Chessboard.this.fields[(row - 1) - FIRST_ROW][column - FIRST_COLUMN].mark();
            }
            
             //HÖGER
             // column är av typen byte. Vid addition av 1 vill den konvertera till int, därav konverteras summan till byte. 
            if (Chessboard.this.isValidField(row, (byte) (column + 1))) {
                Chessboard.this.fields[row - FIRST_ROW][(column + 1) - FIRST_COLUMN].mark();
            }

             //VÄNSTER
            if (Chessboard.this.isValidField(row, (byte) (column - 1))) {
                Chessboard.this.fields[row - FIRST_ROW][(column - 1) - FIRST_COLUMN].mark();
            }

            //Diagonal upp höger.
            if (Chessboard.this.isValidField((char) (row - 1), (byte) (column + 1))) {
                Chessboard.this.fields[(row - 1) - FIRST_ROW][(column + 1) - FIRST_COLUMN].mark();
            }

            //Diagonal upp vänster.
            if (Chessboard.this.isValidField((char) (row - 1), (byte) (column - 1))) {
                Chessboard.this.fields[(row - 1) - FIRST_ROW][(column - 1) - FIRST_COLUMN].mark();
            }

            //Diagonal ner höger.
            if (Chessboard.this.isValidField((char) (row + 1), (byte) (column + 1))) {
                Chessboard.this.fields[(row + 1) - FIRST_ROW][(column + 1) - FIRST_COLUMN].mark();
            }

            //Diagonal ner vänster.
            if (Chessboard.this.isValidField((char) (row + 1), (byte) (column - 1))) {
                Chessboard.this.fields[(row + 1) - FIRST_ROW][(column - 1) - FIRST_COLUMN].mark();
            }

            /* row = nuvarandra rad 

                isValidField('a', 8) => true
                FIRST_COLUMN = 1
                this.fields['a' - FIRST_ROW][8 - FIRST_COLULM] => 

                isValidField(row, column) => 
                this.fields[8][8] => 0 => 7

                row + 1 => går neråt
                row - 1 => går uppåt

                column + 1 => höger
                column -1 => vänster

               column = nuvarandra column

                    [             ]
                    [           ]
                    [           ]                    

            */

            // diagonalt mot höger upp


           
        }

    }
}