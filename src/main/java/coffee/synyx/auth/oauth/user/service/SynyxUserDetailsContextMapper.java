package coffee.synyx.auth.oauth.user.service;

import org.slf4j.Logger;

import org.springframework.ldap.core.DirContextOperations;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

import static java.lang.invoke.MethodHandles.lookup;


@Service
public class SynyxUserDetailsContextMapper extends LdapUserDetailsMapper {

    private static final Logger LOGGER = getLogger(lookup().lookupClass());

    public SynyxUserDetailsContextMapper() {

        super();

        LOGGER.info("//> SynyxUserDetailsContextMapper created");
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
        Collection<? extends GrantedAuthority> authorities) {

        UserDetails details = super.mapUserFromContext(ctx, username, authorities);

        LOGGER.info("//> Mapped user {} from ldap to SynyxUserDetails", username);

        return new SynyxUserDetails((LdapUserDetails) details, ctx.getStringAttribute("mail"));
    }
}
