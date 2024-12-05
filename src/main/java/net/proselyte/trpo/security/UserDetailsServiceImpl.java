package net.proselyte.trpo.security;

import net.proselyte.trpo.entity.Client;
import net.proselyte.trpo.exceptions.NoSuchException;
import net.proselyte.trpo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
//с помощью UserDetailsService мы получаем методы для загрузки информации о пользователе, необходимые для аунтификации
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email).orElseThrow(()->
                new NoSuchException("Client doesn`t exists"));
        return SecurityUser.fromUser(client);//преобразует объект User в объект UserDetails
    }//с помощью метода findByEmail находим пользователя и возвра
}
