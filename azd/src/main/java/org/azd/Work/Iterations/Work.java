package org.azd.Work.Iterations;

import org.azd.Work.Iterations.types.IterationWorkItems;
import org.azd.Work.Iterations.types.TeamSettingsIteration;
import org.azd.Work.Iterations.types.TeamSettingsIterations;
import org.azd.enums.IterationsTimeFrame;
import org.azd.exceptions.AzDException;
import org.azd.exceptions.DefaultParametersException;
import org.azd.helpers.JsonMapper;
import org.azd.interfaces.WorkDetails;
import org.azd.utils.AzDDefaultParameters;
import org.azd.utils.Request;
import org.azd.utils.RequestMethod;
import org.azd.utils.ResourceId;

import java.util.HashMap;
import java.util.Map;

import static org.azd.helpers.URLHelper.encodeSpace;

public class Work implements WorkDetails {
    /***
     * Instance of AzDDefaultParameters
     */
    private final AzDDefaultParameters DEFAULT_PARAMETERS;
    private final JsonMapper MAPPER = new JsonMapper();
    private final String AREA = "work";


    /***
     * Instantiate the class with instance of AzDDefaultParameters
     * @param defaultParameters instance of AzDDefaultParameters
     */
    public Work(AzDDefaultParameters defaultParameters) { this.DEFAULT_PARAMETERS = defaultParameters; }

    /***
     * Get a team's iterations
     * @param teamName Team ID or team name
     * @return {@link TeamSettingsIterations}
     * @throws {@link DefaultParametersException}
     * @throws {@link AzDException}
     */
    @Override
    public TeamSettingsIterations getTeamSettingsIterations(String teamName) throws DefaultParametersException, AzDException {
        String r = Request.request(RequestMethod.GET, DEFAULT_PARAMETERS, ResourceId.WORK,
                (DEFAULT_PARAMETERS.getProject() + "/" + encodeSpace(teamName)),
                AREA,null , "teamsettings/iterations", WorkVersion.VERSION, null, null);

        return MAPPER.mapJsonResponse(r, TeamSettingsIterations.class);
    }

    /***
     * Get a team's iterations using timeframe filter
     * @param teamName Team ID or team name
     * @param timeFrame A filter for which iterations are returned based on relative time.
     * Only 'Current' is supported currently. {@link IterationsTimeFrame}
     * @return {@link TeamSettingsIterations}
     * @throws {@link DefaultParametersException}
     * @throws {@link AzDException}
     */
    @Override
    public TeamSettingsIterations getTeamSettingsIterations(String teamName, IterationsTimeFrame timeFrame) throws DefaultParametersException, AzDException {

        var q = new HashMap<String, Object>(){{
            put("$timeframe", timeFrame.toString().toLowerCase());
        }};

        String r = Request.request(RequestMethod.GET, DEFAULT_PARAMETERS, ResourceId.WORK,
                (DEFAULT_PARAMETERS.getProject() + "/" + encodeSpace(teamName)),
                AREA,null , "teamsettings/iterations", WorkVersion.VERSION, q, null);

        return MAPPER.mapJsonResponse(r, TeamSettingsIterations.class);
    }

    /***
     * Get work items for iteration
     * @param teamName Team ID or team name
     * @param iterationId ID of the iteration
     * @return {@link TeamSettingsIterations}
     * @throws {@link DefaultParametersException}
     * @throws {@link AzDException}
     */
    @Override
    public IterationWorkItems getTeamIterationWorkItems(String teamName, String iterationId) throws DefaultParametersException, AzDException {
        String r = Request.request(RequestMethod.GET, DEFAULT_PARAMETERS, ResourceId.WORK,
                (DEFAULT_PARAMETERS.getProject() + "/" + encodeSpace(teamName)),
                AREA + "/teamsettings/iterations",iterationId , "workitems", WorkVersion.VERSION, null, null);

        return MAPPER.mapJsonResponse(r, IterationWorkItems.class);
    }

    /***
     * Get team's iteration by iterationId
     * @param teamName ID of the iteration
     * @param iterationId Team ID or team name
     * @return {@link TeamSettingsIterations}
     * @throws {@link DefaultParametersException}
     * @throws {@link AzDException}
     */
    @Override
    public TeamSettingsIteration getTeamSettingsIteration(String teamName, String iterationId) throws DefaultParametersException, AzDException {
        String r = Request.request(RequestMethod.GET, DEFAULT_PARAMETERS, ResourceId.WORK,
                (DEFAULT_PARAMETERS.getProject() + "/" + encodeSpace(teamName)),
                AREA + "/teamsettings/iterations",iterationId , null, WorkVersion.VERSION, null, null);

        return MAPPER.mapJsonResponse(r, TeamSettingsIteration.class);
    }

    /***
     * Delete a team's iteration by iterationId
     * @param teamName Team ID or team name
     * @param iterationId ID of the iteration
     * @throws {@link DefaultParametersException}
     * @throws {@link AzDException}
     */
    @Override
    public void deleteTeamSettingsIteration(String teamName, String iterationId) throws DefaultParametersException, AzDException {
        try {
            String r = Request.request(RequestMethod.DELETE, DEFAULT_PARAMETERS, ResourceId.WORK,
                        (DEFAULT_PARAMETERS.getProject() + "/" + encodeSpace(teamName)),
                        AREA + "/teamsettings/iterations", iterationId, null, WorkVersion.VERSION, null, null);
            if (!r.isEmpty()) MAPPER.mapJsonResponse(r, Map.class);
        } catch (DefaultParametersException | AzDException e) {
            throw e;
        }
    }

}