package com.h2healing.schedule.services.cliente;
import com.h2healing.schedule.model.cliente.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ClienteValidationService {

    private static final String TELEFONE_PATTERN = "^[0-9]{10,11}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public List<String> validarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO == null){
            return List.of("ClienteDTO não pode ser nulo");
        }
        List<String> errors = new ArrayList<>();

        String documentoError = validarDocumento(clienteDTO.documento());
        if (documentoError != null) {
            errors.add(documentoError);
        }

        if(clienteDTO.telefone() != null) {
            String telefoneError = validarTelefone(clienteDTO.telefone());
            if (telefoneError != null) {
                errors.add(telefoneError);
            }
        }

        if(clienteDTO.email()!= null) {
            String emailError = validarEmail(clienteDTO.email());
            if (emailError != null) {
                errors.add(emailError);
            }
        }
        return errors;
    }

    // Métodos de validação individuais
    private String validarDocumento(String documento) {
        if (documento == null) {
            return "Documento não pode ser nulo";
        }

        // Remova caracteres não numéricos
        String documentoLimpo = documento.replaceAll("[^0-9]", "");

        if (documentoLimpo.length() == 11) {
            if (!validarCpf(documentoLimpo)) {
                return "CPF inválido";
            }
        } else if (documentoLimpo.length() == 14) {
            if (!validarCnpj(documentoLimpo)) {
                return "CNPJ inválido";
            }
        } else {
            return "Documento inválido";
        }
        return null;
    }

    private String validarTelefone(String telefone) {
        if (telefone == null || !Pattern.matches(TELEFONE_PATTERN, telefone)) {
            return "Telefone inválido";
        }
        return null;
    }

    private String validarEmail(String email) {
        if (email == null|| !Pattern.matches(EMAIL_PATTERN, email)) {
            return "Email inválido";
        }
        return null;
    }
    private boolean validarCpf(String cpf) {
        int dv1 = calcularDigitoVerificadorCpf(cpf.substring(0,9));
        int dv2 = calcularDigitoVerificadorCpf(cpf.substring(0,9) + dv1);
        return cpf.endsWith(Integer.toString(dv1) + Integer.toString(dv2));
    }
    private boolean validarCnpj(String cnpj){
        int dv1 = calcularDigitoVerificadorCnpj(cnpj.substring(0,12));
        int dv2 = calcularDigitoVerificadorCnpj(cnpj.substring(0,12) + dv1);
        return cnpj.endsWith(Integer.toString(dv1) + Integer.toString(dv2));
    }
    private int calcularDigitoVerificadorCpf(String base){
        int soma = 0;
        int multiplicador = base.length() + 1;
        for (char c : base.toCharArray()) {
            soma += Character.getNumericValue(c) * multiplicador--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    private int calcularDigitoVerificadorCnpj(String base){
        int soma = 0;
        int peso = 2;

        for (int i = base.length() - 1; i >= 0; i--) {
            soma += Character.getNumericValue(base.charAt(i)) * peso++;
            if (peso > 9) {
                peso = 2;
            }
        }

        int resto = soma % 11;
        int digito = (resto < 2) ? 0 : (11 - resto);

        return digito;
    }
}
