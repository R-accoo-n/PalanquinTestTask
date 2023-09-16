import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 *
 *
 * Implement in single class.
 */
public class DateSorter {
    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2005-07-01, 2005-01-02, 2005-01-01, 2005-05-03)
     * would sort to
     * (2005-01-01, 2005-01-02, 2005-07-01, 2005-05-03)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */

    /*
    The most trivial way would be a solution using 2 arrays, in which the elements with "R"
    and without it would be composed separately and then, after sorting the arrays accordingly,
    put them into one collection, however, I wanted to demonstrate a solution using
    Comparators, since I consider it better designed than using 2 arrays.
     */
    public static Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {

        return unsortedDates.stream().sorted(DateSorter.getRComparator().thenComparing(DateSorter.getDateComparator())).toList();
    }


    // Place R-months before non-R months
    public static Comparator<LocalDate> getRComparator(){

        return (date1, date2) -> {
            Month month1 = date1.getMonth();
            Month month2 = date2.getMonth();

            if (month1.toString().contains("R") && !month2.toString().contains("R")) {
                return -1;
            } else if (!month1.toString().contains("R") && month2.toString().contains("R")) {
                return 1;
            } else {
                return 0;
            }
        };
    }
    // Sort R-months in ascending order and non-R months in descending order.
    public static Comparator<LocalDate> getDateComparator(){

        return (date1, date2) -> {
            Month month1 = date1.getMonth();
            Month month2 = date2.getMonth();

            if(month1.toString().contains("R") && month2.toString().contains("R")){
                return date1.compareTo(date2);
            }else if(!month1.toString().contains("R") && !month2.toString().contains("R")) {
                return date1.compareTo(date2) * -1;
            }else {
                return 0;
            }
        };
    }


    //Result month order: 1 2 3 4 9 10 11 12 8 7 6 5
    public static void main(String[] args) {
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(LocalDate.of(2023, 1, 15));
        dateList.add(LocalDate.of(2023, 2, 28));
        dateList.add(LocalDate.of(2023, 3, 10));
        dateList.add(LocalDate.of(2023, 4, 5));
        dateList.add(LocalDate.of(2023, 5, 20));
        dateList.add(LocalDate.of(2023, 6, 8));
        dateList.add(LocalDate.of(2023, 7, 17));
        dateList.add(LocalDate.of(2023, 8, 3));
        dateList.add(LocalDate.of(2023, 9, 29));
        dateList.add(LocalDate.of(2023, 10, 12));
        dateList.add(LocalDate.of(2023, 11, 15));
        dateList.add(LocalDate.of(2023, 12, 2));
        dateList.add(LocalDate.of(2023, 1, 14));
        dateList.add(LocalDate.of(2023, 2, 25));
        dateList.add(LocalDate.of(2023, 3, 11));
        dateList.add(LocalDate.of(2023, 4, 6));
        dateList.add(LocalDate.of(2023, 5, 21));
        dateList.add(LocalDate.of(2023, 6, 9));
        dateList.add(LocalDate.of(2023, 7, 18));
        dateList.add(LocalDate.of(2023, 8, 2));
        dateList.add(LocalDate.of(2023, 9, 28));
        dateList.add(LocalDate.of(2023, 10, 13));
        dateList.add(LocalDate.of(2023, 11, 14));
        dateList.add(LocalDate.of(2023, 12, 1));

        System.out.println(dateList);
        dateList = sortDates(dateList).stream().toList();
        System.out.println(dateList);
    }
}

