package prankMessage;

/**
 * Define interraction with the message provider service
 */
public interface IPrankMessageProviderService {

    /**
     * Provide a prank message from a given source, or random
     * @return a prank message
     */
    Message getPrankMessage();
}
