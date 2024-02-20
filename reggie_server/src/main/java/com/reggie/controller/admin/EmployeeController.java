package com.reggie.controller.admin;

import com.reggie.constant.JwtClaimsConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.dto.EmployeePageQueryDTO;
import com.reggie.entity.Employee;
import com.reggie.properties.JwtProperties;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.EmployeeService;
import com.reggie.utils.JwtUtil;
import com.reggie.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ChenXW
 * @Date:2024/2/19 14:23
 * @Description:
 **/

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {


    @Resource
    private EmployeeService employeeService;

    @Resource
    private JwtProperties jwtProperties;


    /**
     * @description: 测试拦截器的方法
     * @author: ChenXW
     * @date: 2024/2/19 18:38
     */
    @GetMapping("/testJwt")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "i", value = "参数一", required = true),
            @ApiImplicitParam(name = "j", value = "参数二", required = false)
    })
    public R<String> testJwt1(int i, int j) {
        return R.success("jwt test");
    }

    @PostMapping("/testJwt")
    public R<String> testJwt2() {
        return R.success("jwt test");
    }


    /**
     * @description: 登录
     * @author: ChenXW
     * @date: 2024/2/19 14:25
     */
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public R<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {

        log.info("员工登录:{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());

        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return R.success(employeeLoginVO);

    }

    /**
     * @description: 退出
     * @author: ChenXW
     * @date: 2024/2/19 19:39
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public R<String> logout() {
        return R.success();
    }

    /**
     * @description: 新增员工
     * @author: ChenXW
     * @date: 2024/2/20 9:43
     */
    @PostMapping
    public R<String> add(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工:{}", employeeDTO);

        employeeService.save(employeeDTO);

        return R.success();
    }

    /**
     * @description: 员工分页查询
     * @author: ChenXW
     * @date: 2024/2/20 12:41
     */
    @ApiOperation("员工分页查询")
    @GetMapping("/page")
    public R<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){

        log.info("员工分页查询:{}",employeePageQueryDTO);

        PageResult pageResult =  employeeService.pageQuery(employeePageQueryDTO);


        return R.success(pageResult);
    }


    /**
     * @description: 启用禁用员工账户
     * @author: ChenXW
     * @date: 2024/2/20 14:06
     */
    @ApiOperation("启用禁用员工账户")
    @PostMapping("/status/{status}")
    public R<String> startOrStop(@PathVariable Integer status,Long id){

        log.info("启用禁用员工账户:{},{}",status,id);

        employeeService.startOrStop(status,id);

        return R.success();
    }

    @ApiOperation("根据id查询员工")
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        return R.success(employeeService.getById(id));
    }

    /**
     * @description: 编辑员工信息
     * @author: ChenXW
     * @date: 2024/2/20 14:36
     */
    @ApiOperation("编辑员工信息")
    @PutMapping
    public R<String> update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工:{}",employeeDTO);
        employeeService.update(employeeDTO);
        return R.success();
    }

}
