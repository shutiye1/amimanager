package safariami.manager.service;

import java.math.BigDecimal;

public interface STSTokenGeneratorService {

	public String generateToken(BigDecimal amount, String serialNo) throws Exception;
	public String generateTamperToken(String serialNo) throws Exception;
}
