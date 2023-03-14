# subtask 2 - Custom Configuration

DataSourceConfiguration is now conditional on properties from application.yaml and also provides ConditionalOnMissingBean 
DataSource.

There are two JDBC DataSource tests:
- DataSourceConfigurationTest - tests DataSource created by DataSourceConfiguration with properties from @TestPropertySource
- DataSourceConfigurationFailureTest - verifies that conditional DataSource isn't created, because required property 
is missing, and default spring test DataSource is provided instead.