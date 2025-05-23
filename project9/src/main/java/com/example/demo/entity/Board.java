package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity { // 등록시간필드와 수정시간필드 상속받기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no; //글번호

    @Column(length = 100, nullable = false)
    String title; //제목

    @Column(length = 1500, nullable = false)
    String content; //내용
    
    // 작성자는 회원의 아이디를 참조
    // 회원은 여러개의 글을 작성할수 있음 => 관계차수 (1:n)
    // 외래키 설정 방법
    // 필드 자료형 변경
    // 관계 차수 설정
    @ManyToOne // 관계차수
    Member writer; // 자료형을 Member로 변경 (fk 설정)

}
