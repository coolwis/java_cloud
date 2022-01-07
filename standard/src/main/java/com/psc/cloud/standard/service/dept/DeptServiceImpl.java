package com.psc.cloud.standard.service.dept;

import com.psc.cloud.standard.controller.dto.ResponseDeptDto;
import com.psc.cloud.standard.core.exception.BusinessException;
import com.psc.cloud.standard.domain.dept.Dept;
import com.psc.cloud.standard.domain.dept.DeptRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DeptServiceImpl implements DeptService {
    final DeptRepository deptRepository; // Autowired 효과를 냄, final 선언,  코드가 잘 못 되었을때 바로 확인 됨.

    @Override
    public List<ResponseDeptDto> deptList() throws BusinessException {

        List<ResponseDeptDto> responseDeptDtoList =new ArrayList<>();
        deptRepository.findAll().forEach(dept-> {
            log.debug(dept.toString());
            responseDeptDtoList.add(new ResponseDeptDto(dept));
        });
        return responseDeptDtoList;
    }

    @Override
    public ResponseDeptDto  deptDetail(Integer deptno) throws BusinessException {

        Optional<Dept> optionalDept= deptRepository.findById(deptno);
        Dept dept  = optionalDept.orElseThrow(()-> new BusinessException("not exist dept"));
        return new ResponseDeptDto(dept);
    }

    @Override
    @Transactional
    public ResponseDeptDto deptInsert(Dept dept) throws BusinessException {
        Optional<Dept> optionalDept=deptRepository.findById(dept.getDeptno());
        if(!optionalDept.isPresent()){
            deptRepository.save(dept);
        } else {
            throw new BusinessException("해당 부서번호가 이미 존재함");
        }
        return  new ResponseDeptDto(dept);
    }

    @Override
    @Transactional
    public ResponseDeptDto deptUpdate(Dept dept) throws BusinessException {
        Optional<Dept> optionalDept=deptRepository.findById(dept.getDeptno());
        if(optionalDept.isPresent()){
            deptRepository.save(dept);
        } else {
            throw new BusinessException("해당 부서번호가 존재하지 않습니다, deptno:" +dept.getDeptno());
        }
        return  new ResponseDeptDto(dept);
    }

    @Override
    @Transactional
    public void deptDelete(Integer deptno) throws BusinessException {
        deptRepository.deleteById(deptno);
    }
}
