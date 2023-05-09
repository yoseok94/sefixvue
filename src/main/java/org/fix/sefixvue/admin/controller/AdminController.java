package org.fix.sefixvue.admin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Dept;
import org.fix.sefixvue.admin.model.dto.DeptDto;
import org.fix.sefixvue.admin.model.service.AdminService;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class AdminController {
    private final AdminService adminService;

    //View lists by page
    @GetMapping("/dept/list")
    public Header<List<DeptDto>> deptList(
            @PageableDefault(sort = {"deptno"}) Pageable pageable,
            SearchCondition searchCondition) {
        return adminService.getDeptList(pageable, searchCondition);
    }



    @PostMapping("/dept")
    public Dept create(@RequestBody DeptDto deptDto) {
        return adminService.create(deptDto);
    }

    @PatchMapping("/board")
    public Dept update(@RequestBody DeptDto deptDto){
        return  adminService.update(deptDto);
    }

    @DeleteMapping("/dept/{id}")
    public void delete(@PathVariable Long id){
        adminService.delete(id);
    }


}