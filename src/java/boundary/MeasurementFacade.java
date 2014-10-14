/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Measurement.Measurement;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kaikkonen
 */
@Stateless
public class MeasurementFacade extends AbstractFacade<Measurement> {
    @PersistenceContext(unitName = "SimpleMeasurementsAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeasurementFacade() {
        super(Measurement.class);
    }
    
}
