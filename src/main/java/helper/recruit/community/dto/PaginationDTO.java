package helper.recruit.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        // total count is the number of job list in the question database
        // page number
        // size is 5 : one page 5 rows
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

//        if (page<1)
//            page = 1;
//        if (page > totalPage)
//            page = totalPage;

        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        // 第一页的时候不能再向前翻页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        // 最后一页的时候不能再往后翻页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 页码不包含第一页的时候，可以直接跳转到最前
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        // 页码不包含最后一页的时候，可以直接跳转到最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
