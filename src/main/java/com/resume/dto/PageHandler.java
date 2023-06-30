package com.resume.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PageHandler {
    private int totalCnt; // 총 게시물 개수
    private int pageSize; // 한 페이지의 크기
    private int naviSize =10; // 페이지 내비게이션의 크기
    private int totalPage; //전체 페이지의 개수
    private int page; // 현재 페이지
    private int beginPage; // 네비게이션의 첫번째 페이지
    private int endPage;
    private boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄 것인지
    private boolean showNext;

    public PageHandler(int totalCnt, int page){
        this(totalCnt,page,10);
    }
    public PageHandler(int totalCnt,int page,int pageSize){
        this.totalCnt =totalCnt;
        this.page = page;
        this.pageSize = pageSize;
        totalPage = (int)Math.ceil(totalCnt/(double)pageSize);
        beginPage = (page-1)/naviSize*naviSize +1;
        endPage = Math.min(beginPage+naviSize-1, totalPage);
        showPrev = beginPage !=1;
        showNext = endPage != totalPage;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageHandler that = (PageHandler) o;
        return totalCnt == that.totalCnt && pageSize == that.pageSize && naviSize == that.naviSize && totalPage == that.totalPage && page == that.page && beginPage == that.beginPage && endPage == that.endPage && showPrev == that.showPrev && showNext == that.showNext;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCnt, pageSize, naviSize, totalPage, page, beginPage, endPage, showPrev, showNext);
    }
}
