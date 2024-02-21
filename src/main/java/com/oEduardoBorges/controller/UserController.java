package com.oEduardoBorges.controller;

import com.oEduardoBorges.dto.request.user.UserRequestUpdate;
import com.oEduardoBorges.dto.response.tela.TelaResponse;
import com.oEduardoBorges.dto.response.user.UserResponse;
import com.oEduardoBorges.dto.response.user.UserRoleResponse;
import com.oEduardoBorges.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> userList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponse> users = userService.userList(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> userUpdate(@PathVariable Long id, @RequestBody UserRequestUpdate userRequest){
        UserResponse userResponse = userService.userUpdate(id, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("role/{id}")
    public ResponseEntity<Optional<UserRoleResponse>> findUserRoleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserRoleById(id));
    }

    @PutMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.addRoleToUser(userId, roleId);
        return ResponseEntity.status(HttpStatus.OK).body("Role added to user successfully");
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.status(HttpStatus.OK).body("Role removed from user successfully");
    }
}
