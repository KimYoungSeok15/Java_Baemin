package com.example.shoppingmall.member;

import com.example.shoppingmall.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.shoppingmall.utils.ApiUtils.error;
import static com.example.shoppingmall.utils.ApiUtils.success;

@Slf4j
@AllArgsConstructor
@RestController
public class MemberController {

    MemberService memberService;

//    @GetMapping("/datasource")
//    public void makeConnection(){
//        memberService.makeConnection();
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException errors) {
        Map<String, String> errorMap = new HashMap<>();
        errors.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
        return ApiUtils.error(errorMap, HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("/join/res/en") // Before
//    public ResponseEntity<String> joinByResponseEntity(@RequestBody MemberDTO memberDTO) {
//
//        if(isDuplicateId(memberDTO))
//            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
//
//        Member requestMember = new Member(memberDTO);
//        String userId = memberService.join(requestMember);
//        return new ResponseEntity<>(userId, HttpStatus.OK);
//    }

    @PostMapping("/join") // After
    public ApiUtils.ApiResult join(@RequestBody @Valid MemberDTO memberDTO) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getAllErrors().forEach(error -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            });
//            return ApiUtils.error(errors, HttpStatus.BAD_REQUEST);
//        }

        Map<String, String> errorMessage = new HashMap<>();
        if(isDuplicateId(memberDTO)) {
            errorMessage.put("id", "중복된 아이디입니다.");
            return error(errorMessage, HttpStatus.CONFLICT);
        }

        Member requestMember = memberDTO.convertToEntity();
        String userId = memberService.join(requestMember);
        return success(userId);
    }

    @PostMapping("/login")
    public ApiUtils.ApiResult login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ApiUtils.error(errors, HttpStatus.BAD_REQUEST);
        }

        Member loginedMember = memberService.findByUserId(loginDTO);
        Map<String, String> errorMessage = new HashMap<>();
        if (loginedMember == null){
            errorMessage.put("message", "아이디가 틀립니다.");
            return error(errorMessage, HttpStatus.NO_CONTENT);
        }

        if (Objects.equals(loginedMember.getPw(), loginDTO.getPw())){
            return success(loginedMember);
        };
        errorMessage.put("message", "비밀번호가 틀립니다.");
        return error(errorMessage, HttpStatus.BAD_REQUEST);
    }


    private boolean isDuplicateId(MemberDTO memberDTO) {
        return memberService.checkDuplicateId(memberDTO.getUserId());
    }
}
