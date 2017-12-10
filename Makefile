JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) *.java

default: .java.class

makeclient: $(JC) Client.java

clean:
	$(RM) *.class

client:
	$(JC) Client.java

run:
	java Client
