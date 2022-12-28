package servises;

import repo.TestRepo;

public class PaginationService {
    TestRepo testRepo = new TestRepo();


    public int countPages(Integer countRows, Integer rowsOnPage) {
        if (countRows % rowsOnPage != 0) {
            return countRows / rowsOnPage + 1;
        }
        return countRows / rowsOnPage;
    }


}
