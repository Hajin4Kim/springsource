package com.example.mart.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.mart.entity.cascade.Child;
import com.example.mart.entity.cascade.Parent;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.cascade.ChildRepository;
import com.example.mart.repository.cascade.ParentRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentRepositoryTest {
  @Autowired
  ParentRepository parentRepository;

  @Autowired
  ChildRepository childRepository;

  @Test
  public void parentInsertTest() {
    Parent parent = Parent.builder().name("parent3").build();

    LongStream.rangeClosed(1, 3).forEach(i -> {
      Child child = Child.builder().name("child" + i).parent(parent).build();

      // parent.getChildren().add(child); // TODO: list 에 붙인 후에 Parent삽입.save()
      // //child 추가되지않음
      childRepository.save(child);
    });
    // parentRepository.save(parent);

  }

  @Test
  public void parentInsertTest2() {
    Parent parent = Parent.builder().name("parent3").build();

    LongStream.rangeClosed(1, 3).forEach(i -> {
      Child child = Child.builder().name("child" + i).parent(parent).build();

      parent.getChildren().add(child); // TODO: list 에 붙인 후에 Parent삽입.save()
      // //child 추가되지않음
    });
    parentRepository.save(parent);

  }

  @Test
  public void testChildRead() {
    // 자식2 이용 정보 조회 (+부모조회)
    Child child = childRepository.findById(2L).get();
    System.out.println(child);
    System.out.println(child.getParent());
  }

  @Test
  public void testParentDelete() {
    // // 부모 삭제 시 관련되어있는 자식 같이 삭제
    // // 자식삭제 코드를 작성하지 않고

    // 무결성 제약조건이 위배되었습니다- 자식 레코드가 발견되었습니다
    // TODO: 부모쪾에 CascadeType.ALL 해두면 됨
    parentRepository.deleteById(3L);
  }

  @Commit
  @Transactional // TODO: OneToMany 는 원래 LAZY
  @Test
  public void testParentDelete2() {
    // // 부모 삭제 시 관련되어있는 자식 같이 삭제
    // // 자식삭제 코드를 작성하지 않고

    Parent parent = parentRepository.findById(1L).get();

    // TODO: LazyInitializationException: failed to lazily initialize a collection
    // of role
    // TODO: 자식만 삭제, 자식의 일부만 부모를 이용해 삭제
    parent.getChildren().remove(0);
    // parent.getChildren().remove(1);
    // parent.getChildren().remove(2);
    parentRepository.save(parent);

  }

}
