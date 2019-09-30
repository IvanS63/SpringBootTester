package com.myapp.clientservice.hoverfly;

import static com.myapp.clientservice.hoverfly.bookstore.BookstoreStubs.getTopAuthors;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;

import io.specto.hoverfly.junit.core.SimulationSource;
import lombok.experimental.UtilityClass;

/**
 * Stubs for imitating other services responses.
 *
 * @author Ivan_Semenov
 */
@UtilityClass
public class HoverflyStubs {
    
    public static final SimulationSource SIMULATION_SOURCE = dsl(
            getTopAuthors());
}
