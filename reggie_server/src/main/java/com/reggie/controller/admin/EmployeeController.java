package com.reggie.controller.admin;

import com.reggie.constant.JwtClaimsConstant;
import com.reggie.dto.EmployeeDTO;
import com.reggie.dto.EmployeeLoginDTO;
import com.reggie.entity.Employee;
import com.reggie.properties.JwtProperties;
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

}
