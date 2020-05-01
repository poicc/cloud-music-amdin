package com.soft1851.music.admin.validator;

import com.soft1851.music.admin.annotation.UsernameAttribute;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.mapper.SysAdminMapper;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;

/**
 * @author CRQ
 */
public class UserNameValidator implements ConstraintValidator<UsernameAttribute,String>{

    @Resource
    private SysAdminMapper sysAdminMapper;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<SysAdmin> sysAdmins = sysAdminMapper.getAllName();
        String name1 = sysAdmins.get(0).getName();
        String name2 = sysAdmins.get(1).getName();
        HashSet<Object> regions = new HashSet<>();
        regions.add(name1);
        regions.add(name2);
        return regions.contains(value);
    }
}
