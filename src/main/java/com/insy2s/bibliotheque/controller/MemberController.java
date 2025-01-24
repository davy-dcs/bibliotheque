package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.domain.Member;
import com.insy2s.bibliotheque.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;


@RestController
@RequestMapping("/members")
public class MemberController {;
    @GetMapping
    public ResponseEntity<HashSet<Member>> get() {
        return ResponseEntity.status(HttpStatus.OK).body(MemberService.getMembers());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody Member member) {
        return MemberService.add(member) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Member> getByUuid(@PathVariable UUID uuid) {
        if (MemberService.getByUuid(uuid) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(MemberService.getByUuid(uuid));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> put(@PathVariable UUID uuid, @RequestBody Member member) {
        return MemberService.put(uuid, member) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        return MemberService.delete(uuid) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
