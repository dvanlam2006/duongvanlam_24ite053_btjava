package oop5;

    public class MyDate {
        private int year;
        private int month;
        private int day;

        private static final String[] MONTHS =
                {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        private static final String[] DAYS =
                {"Sunday", "Monday", "Tuesday", "Wednesday",
                        "Thursday", "Friday", "Saturday"};

        private static final int[] DAYS_IN_MONTHS =
                {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        public MyDate(int year, int month, int day) {
            setDate(year, month, day);
        }

        public static boolean isLeapYear(int year) {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        }

        public static boolean isValidDate(int year, int month, int day) {
            if (year < 1 || year > 9999) return false;
            if (month < 1 || month > 12) return false;

            int maxDay = DAYS_IN_MONTHS[month - 1];
            if (month == 2 && isLeapYear(year)) maxDay = 29;

            return day >= 1 && day <= maxDay;
        }

        public static int getDayOfWeek(int year, int month, int day) {
            if (!isValidDate(year, month, day)) return -1;

            int y = (month < 3) ? year - 1 : year;
            int m = (month < 3) ? month + 12 : month;
            int k = y % 100;
            int j = y / 100;

            int h = (day + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) - (2 * j)) % 7;
            return (h + 5) % 7;
        }

        public void setDate(int year, int month, int day) {
            if (isValidDate(year, month, day)) {
                this.year = year;
                this.month = month;
                this.day = day;
            } else {
                throw new IllegalArgumentException("Invalid date!");
            }
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public void setYear(int year) {
            if (year >= 1 && year <= 9999) {
                this.year = year;
            } else {
                throw new IllegalArgumentException("Invalid year!");
            }
        }

        public void setMonth(int month) {
            if (month >= 1 && month <= 12) {
                this.month = month;
            } else {
                throw new IllegalArgumentException("Invalid month!");
            }
        }

        public void setDay(int day) {
            if (isValidDate(year, month, day)) {
                this.day = day;
            } else {
                throw new IllegalArgumentException("Invalid day!");
            }
        }

        @Override
        public String toString() {
            int dayOfWeek = getDayOfWeek(year, month, day);
            return DAYS[dayOfWeek] + " " + day + " " + MONTHS[month - 1] + " " + year;
        }

        public MyDate nextDay() {
            day++;
            if (!isValidDate(year, month, day)) {
                day = 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
            return this;
        }

        public MyDate nextMonth() {
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
            if (!isValidDate(year, month, day)) {
                day = DAYS_IN_MONTHS[month - 1];
                if (month == 2 && isLeapYear(year)) day = 29;
            }
            return this;
        }

        public MyDate nextYear() {
            year++;
            if (!isValidDate(year, month, day) && month == 2 && day == 29) {
                day = 28;
            }
            return this;
        }

        public MyDate previousDay() {
            day--;
            if (day < 1) {
                month--;
                if (month < 1) {
                    month = 12;
                    year--;
                }
                day = DAYS_IN_MONTHS[month - 1];
                if (month == 2 && isLeapYear(year)) day = 29;
            }
            return this;
        }

        public MyDate previousMonth() {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }
            if (!isValidDate(year, month, day)) {
                day = DAYS_IN_MONTHS[month - 1];
                if (month == 2 && isLeapYear(year)) day = 29;
            }
            return this;
        }

        public MyDate previousYear() {
            year--;
            if (!isValidDate(year, month, day) && month == 2 && day == 29) {
                day = 28;
            }
            return this;
        }
    }

