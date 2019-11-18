package Business;

import com.intrinio.api.ForexApi;
import com.intrinio.api.IndexApi;
import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.ApiException;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.ApiResponseForexPrices;
import com.intrinio.models.ApiResponseSecurityStockPrices;
import com.intrinio.models.ForexPrice;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;

public class IntrinioAPI implements Mercado {

	public double getCotacaoAcao(String identifier) {
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

	@Override
	public double getCotacaoCommodity(String identifier) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
		auth.setApiKey("OjcxNjhjYzQ5NzAwMThlNTlmZTI5YWI4M2NlZGFhMmI1");

		IndexApi indexApi = new IndexApi();

		// String identifier = "$SIC.1"; // String | An Index Identifier (symbol, Intrinio ID)
		String tag = "level"; // String | An Intrinio data tag ID or code-name

		try {
			BigDecimal result = indexApi.getSicIndexDataPointNumber(identifier, tag);
			System.out.println(result);
			return result.doubleValue();
		} catch (ApiException e) {
			System.err.println("Exception when calling IndexApi#getSicIndexDataPointNumber");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double getCotacaoIndice(String identifier) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
		auth.setApiKey("OjcxNjhjYzQ5NzAwMThlNTlmZTI5YWI4M2NlZGFhMmI1");

		IndexApi indexApi = new IndexApi();

		//String identifier = "$GDP"; // String | An Index Identifier (symbol, Intrinio ID)
		String tag = "level"; // String | An Intrinio data tag reference [see - https://data.intrinio.com/data-tags/economic]

		try {
			BigDecimal result = indexApi.getEconomicIndexDataPointNumber(identifier, tag);
			return result.doubleValue();
		} catch (ApiException e) {
			System.err.println("Exception when calling IndexApi#getEconomicIndexDataPointNumber");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double getCotacaoMoeda(String identifier) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
		auth.setApiKey("OjcxNjhjYzQ5NzAwMThlNTlmZTI5YWI4M2NlZGFhMmI1");

		ForexApi forexApi = new ForexApi();

		//String pair = "EURUSD"; // String | The Forex Currency Pair code
		String timeframe = "D1"; // String | The time interval for the quotes
		String timezone = "UTC"; // String | Returns trading times in this timezone
		LocalDate startDate = LocalDate.now().minusDays(2); // LocalDate | Return Forex Prices on or after this date
		String startTime = null; // String | Return Forex Prices at or after this time (24-hour)
		LocalDate endDate = LocalDate.now(); // LocalDate | Return Forex Prices on or before this date
		String endTime = null; // String | Return Forex Prices at or before this time (24-hour)
		Integer pageSize = 100; // Integer | The number of results to return
		String nextPage = null; // String | Gets the next page of data from a previous API call

		try {
			ApiResponseForexPrices result = forexApi.getForexPrices(identifier, timeframe, timezone, startDate, startTime, endDate, endTime, pageSize, nextPage);
			return result.getPrices().get(0).getOpenBid().doubleValue();
		} catch (ApiException e) {
			System.err.println("Exception when calling ForexApi#getForexPrices");
			e.printStackTrace();
		}
		return 0;
	}
}