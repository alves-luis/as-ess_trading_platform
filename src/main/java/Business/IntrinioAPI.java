package Business;

import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.ApiResponseSecurityStockPrices;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;

public class IntrinioAPI implements Mercado {

	public double getCotacao(String identifier) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
		auth.setApiKey("OjcxNjhjYzQ5NzAwMThlNTlmZTI5YWI4M2NlZGFhMmI1");

		SecurityApi securityApi = new SecurityApi();

		// String | A Security identifier (Ticker, FIGI, ISIN, CUSIP, Intrinio ID)
		LocalDate startDate = LocalDate.now().minusDays(2); // LocalDate | Return prices on or after the date
		LocalDate endDate = LocalDate.now(); // LocalDate | Return prices on or before the date
		String frequency = "daily"; // String | Return stock prices in the given frequency
		Integer pageSize = 100; // Integer | The number of results to return
		String nextPage = null; // String | Gets the next page of data from a previous API call

		try {
			ApiResponseSecurityStockPrices result = securityApi.getSecurityStockPrices(identifier, startDate, endDate, frequency, pageSize, nextPage);
			BigDecimal close = result.getStockPrices().get(0).getClose();
			System.out.println("[" + identifier + "] " + close + "€");
			return close.doubleValue();
		} catch (ApiException e) {
			System.err.println("Exception when calling SecurityApi#getSecurityStockPrices");
			e.printStackTrace();
		}
		return 0;
	}
}