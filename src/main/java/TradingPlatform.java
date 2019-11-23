import Business.Facade.TradingPlatformNegociador;
import Presentation.TextUINegociador;
import Presentation.UINegociador;

public class TradingPlatform {
    public static void main(String[] args) {
        UINegociador neg = new TextUINegociador(new TradingPlatformNegociador());
        neg.start();
    }
}
