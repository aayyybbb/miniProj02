package org.kosa.hello.miniproj02.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageResponseVO<E> {
    private int pageNo;//페이지번호
    private int size;  //페이지의 건수
    private int total; //전체건수

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> list;

    @Builder(builderMethodName = "withAll")
    public PageResponseVO(List<E> list, int total, int pageNo, int size) {
        this.pageNo = pageNo;
        this.size = size;

        this.total = total;
        this.list = list;

        // Calculate start and end page numbers for the current page range
        this.start = Math.max(1, pageNo - 4); // Adjusted to show 5 page numbers
        this.end = Math.min((int) Math.ceil(total / (double) size), pageNo + 4); // Adjusted to show 5 page numbers

        // Check if there is a previous page
        this.prev = pageNo > 1;

        // Check if there is a next page
        this.next = total > end * size;
    }
}
