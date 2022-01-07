package com.psc.cloud.standard.domain.dept;

import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
//@ActiveProfiles({"dev", "db-maria"})
@ActiveProfiles({"dev", "db-h2"})

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptRepositoryTest {

    @Autowired
    private  DeptRepository deptRepository;

    @Test
    @Order(1)
    @Commit
    public  void A001_Insert(){
        List deptList =new ArrayList<Dept>();
        deptList.add(Dept.builder().deptno(10).dname("joy").loc("new york").build());
        deptList.add(Dept.builder().deptno(20).dname("research").loc("dallas").build());
        deptList.add(Dept.builder().deptno(30).dname("sales").loc("la").build());
        deptRepository.saveAll(deptList);

        Assertions.assertThat(deptRepository.findById(10).isPresent()).isEqualTo(true);
    }

    @Test
    @Order(2)
    @Commit
    public  void A001_Update(){

        String updateName ="peter";
        deptRepository.save(Dept.builder().deptno(10).dname(updateName).loc("new york").build());
        Dept dept = deptRepository.findById(10).get();

        Assertions.assertThat(dept.getDname()).isEqualTo(updateName);
    }


}