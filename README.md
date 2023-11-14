<h1 align = "center"> Java-Reflection </h1>

1. Create an annotation @AutoInjectable

2. Sort out (Google search) with the Properties class

3. Create an Injector class in which there would be a parameterized inject method that would take an object of any class as a parameter and, using reflection mechanisms, search for fields marked with this annotation (some interface is used as the field type), and initialize these fields with instances of classes that are specified as the implementation of the corresponding interface in some settings file(properties)
