# subtask 4 - Spring Profiles

I intentionally didn't create two configuration classes for two data sources beans. Because I believe spring mechanism 
of defining separate application.yaml file for each profile suits here perfectly.

It is tested be adding `INIT=RUNSCRIPT` to the h2 url. I specified different schema files for QA and DEV profiles. So
there is no need to write two same configuration classes with different properties.

See DevDataSourceTest and QADataSourceTest.