package io.lin.qrgenie;

import com.poiji.annotation.ExcelCell;

public class Participant {
        @ExcelCell(13)
        public String registrationType;
        @ExcelCell(14)
        public String foodOption;

        public String modeOfRegistration;

        @ExcelCell(7)
        public String studentNumber;
        @ExcelCell(6)
        public String name;
        @ExcelCell(2)
        public String dateOfRegistration;
        @ExcelCell(11)
        public String email;
}

