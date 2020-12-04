package de.aicard;

public class Enums {

    public enum State{
        NEW,
        PROCESSING,
        LEARNED
    }

    // Bachelor Degree Subjects at FHE, no all letter capitalisation for improved readability
    public enum Faculty{
        AppliedComputerScience,
        Architecture,
        CivilEngineering,
        ManagementAndLeadershipInEarlyChildhoodEducation,
        BusinessAdministration,
        ForestryAndEcosystemManagement,
        Horticulture,
        BuildingServicesEngineering,
        LandscapeArchitecture,
        EarlyChildhoodEducation,
        SocialWork,
        UrbanAndSpatialPlanningFoundations,
        RailwayEngineering,
        EnergyEngineering,
        TrafficTransportationAndLogisticsEngineering
        }

    public enum CARDKNOWLEDGELEVEL{
        NOINFORMATION,
        SOMEINFORMATION,
        KNOW,
        KNOWWELL,
        KNOWVERYWELL
    }
}
