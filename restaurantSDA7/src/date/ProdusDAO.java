package date;

import java.util.List;

public interface ProdusDAO {

	void setup();

	Produs getProdusById(Integer idProdus);

	void exit();

	Object createProdus(Produs produs);

	List readAllProducts();

	void update(Produs p);

}