# Rest api url


## Dept



    {POST [/Dept]}: addDepartment(Dept)
	{PUT [/Dept/{id}]}: updateDepartment(Dept,int)
	{DELETE [/Dept]}: deleteAllDepartment()
	{GET [/Dept/{id}]}: getDepartment(int)
	{GET [/Dept]}: getDepartment()
	{DELETE [/Dept/{id}]}: deleteDepartment(int)
	{PATCH [/Dept]}: upsert(Dept,int)


## Employees


    {GET [/employees]}: getAllEmployees()
	{GET [/employees/{id}]}: getEmployeeById(int)
	{GET [/employees/department/{did}]}: getEmployeeByDeptId(int)
	{GET [/employees/{eid}/department/{did}]}: getEmployeeByIdAndDeptId(int,int)
	{PUT [/employees/{eid}/project/{pid}]}: assignProjectsToEmployee(int,int)
	{DELETE [/employees/{id}]}: deleteEmployee(int)
	{GET [/employees/project/{project_id}]}: getEmployeebyProjectId(int)
	{POST [/employees]}: saveEmployee(emp)
	{GET [/employees/deptDetails/{empid}]}: getDeptDetailsOfEmp(int)
	{PUT [/employees/{eid}/department/{did}]}: updateEmployee(emp,int,int)


## Project
    
    {PUT [/Project/{pid}/employee/{eid}]}: assignEmployeeToProject(int,int)
    {GET [/Project/employee/{empId}]}: getProjectbyEmpId(int)
    {GET [/Project]}: getAllProject()
    {GET [/Project/{ProjectId}]}: getProjectById(int)
    {GET [/Project/department/{did}]}: getProjectByDeptId(int)
    {POST [/Project/Dept/{deptId}]}: addProjectWithDeptId(project,int)
    {POST [/Project/emp/{empId}]}: addProjectWithEmp(project,int)
    {PUT [/Project/{project_id}/updateDepartment/{dept_id}]}: updateDepartmentId(int,int)
    {DELETE [/Project]}: deleteAllProjects()
    {POST [/Project]}: addProject(project)
