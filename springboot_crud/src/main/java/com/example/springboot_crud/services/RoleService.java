package com.example.springboot_crud.services;

import com.example.springboot_crud.entity.Roles;
import com.example.springboot_crud.exceptions.RoleNotFoundException;
import com.example.springboot_crud.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Roles getRolesById(int roleId) {
        return rolesRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(String.format("Role with roleId %s not found", roleId)));
    }

    public Roles postRole(Roles role) {

        rolesRepository.findById(role.getRoleId()).orElseThrow(() -> new RoleNotFoundException(String.format("Role with roleId %s not found", role.getRoleId())));
        return rolesRepository.save(role);
    }

    public boolean deleteRole(int roleId) {

        Roles roles = rolesRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(String.format("Role with roleId %s not found", roleId)));
        rolesRepository.deleteById(roleId);
        return true;
    }

    public Roles putRoles(Roles newRole){
        Roles existingRole = rolesRepository.findById(newRole.getRoleId()).orElseThrow(() -> new RoleNotFoundException(String.format("Role with roleId %s not found", newRole.getRoleId())));

        String roleName = newRole.getRoleName();
        int roleId = newRole.getRoleId();
        if (roleName != null && !roleName.isBlank()){
            existingRole.setRoleName(roleName);
        }

        return rolesRepository.save(existingRole);
    }

}
