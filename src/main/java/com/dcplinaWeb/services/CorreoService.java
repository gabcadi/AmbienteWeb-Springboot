
package com.dcplinaWeb.services;

import jakarta.mail.MessagingException;


public interface CorreoService {

    public void enviarCorreoHtml(String para,
            String asunto,
            String contenidoHtml)
            throws MessagingException;

}
