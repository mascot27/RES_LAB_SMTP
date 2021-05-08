package victims;

import java.util.List;

/**
 * Define how we get all the addresses of the victims
 */
public interface IVictimsProviderService {

    /**
     * Provide a list of emails entered by the user in a separate file
     * @return a list of victims
     */
    List<String> GetVictims();
}
