//package org.innov.sam.domain.security;
//
//import com.google.common.collect.Sets;
//
//import java.util.Set;
//
//import static org.innov.sam.domain.security.ApplicationUserPermission.USER_READ;
//import static org.innov.sam.domain.security.ApplicationUserPermission.USER_WRITE;
//
//public enum ApplicationUserRole {
//    Analyste(Sets.newHashSet(USER_READ)),
//    Admin(Sets.newHashSet( USER_READ ,USER_WRITE ));
//
//
//    private final Set<ApplicationUserPermission> permissions ;
//
//
//    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<ApplicationUserPermission> getPermissions() {
//        return permissions;
//    }
//}
