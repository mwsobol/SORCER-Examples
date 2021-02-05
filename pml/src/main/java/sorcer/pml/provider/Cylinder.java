package sorcer.pml.provider;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

/**
 * @author Mike Sobolewski
 * 
 */
@SuppressWarnings("rawtypes")
public interface Cylinder {

	Context getCylinderSurface(Context context) throws ContextException, RemoteException;

	Context getCylinderVolume(Context context) throws ContextException, RemoteException;
}
