package com.rizwan.emailWriter_sb.Controller;

import com.rizwan.emailWriter_sb.DTOS.EmailRequest;
import com.rizwan.emailWriter_sb.Services.EmailGeneratedService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailGeneratedController {
    private final EmailGeneratedService emailGeneratedService;
    @PostMapping("/generate")
    ResponseEntity<String> generatedEmail(@RequestBody EmailRequest request){
        return ResponseEntity.ok(emailGeneratedService.generatedEmailReply(request));


    }
}
