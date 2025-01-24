package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.Member;
import lombok.Getter;

import java.util.HashSet;
import java.util.UUID;

public class MemberService {
    @Getter private static final HashSet<Member> members = new HashSet<>();

    public static boolean add(Member member) {
        return members.add(member);
    }

    public static Member getByUuid(UUID uuid) {
        for (Member member : members) {
            if (member.getUuid().equals(uuid)) {
                return member;
            }
        }
        return null;
    }

    public static boolean put(UUID uuid, Member member) {
        for (Member m : members) {
            if (m.getUuid().equals(uuid)) {
                if (member.getFirstname() != null) {
                    m.setFirstname(member.getFirstname());
                }
                if (member.getLastname() != null) {
                    m.setLastname(member.getLastname());
                }
                if (member.getEmail() != null) {
                    m.setEmail(member.getEmail());
                }
                return true;
            }
        }
        return false;
    }

    public static boolean delete(UUID uuid) {
        return members.removeIf(member -> member.getUuid().equals(uuid));
    }
}
