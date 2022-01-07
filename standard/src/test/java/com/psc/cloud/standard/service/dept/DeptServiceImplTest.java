package com.psc.cloud.standard.service.dept;

import com.psc.cloud.standard.controller.dto.ResponseDeptDto;
import com.psc.cloud.standard.domain.dept.Dept;
import com.psc.cloud.standard.domain.dept.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ActiveProfiles({"dev", "db-h2"})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptServiceImplTest {

    @Autowired
    DeptRepository deptRepository;


    @Autowired
    DeptService deptService;

    @Test
    @Order(1)
    @Commit
    @Transactional
    public void A001_DEPT_INSERT() throws Exception  {
        List<Dept> deptList  =  new ArrayList<>();
        deptList.add(Dept.builder().deptno(10).dname("Account").loc("new york").build());
        deptList.add(Dept.builder().deptno(20).dname("Research").loc("dallas").build());
        deptList.add(Dept.builder().deptno(30).dname("sales").loc("chicago").build());
        deptList.add(Dept.builder().deptno(40).dname("operations").loc("boston").build());
        for(Dept dept:deptList){
            deptService.deptInsert(dept);
        }

        for(Dept dept:deptList){
            Integer deptno  = dept.getDeptno();
            ResponseDeptDto  responseDeptDto= deptService.deptDetail(deptno);
            Assertions.assertThat(responseDeptDto.getDeptno()).isEqualTo(deptno);
        }
    }

}