CXX := g++

CXXFLAGS := -Wall -Wextra -Werror -std=c++17

LDFLAGS :=

BUILD := ./build
OBJ_DIR := $(BUILD)/objects
APP_DIR := $(BUILD)

TARGET := programa

SRC := $(wildcard *.cpp)

OBJECTS := $(SRC:%.cpp=$(OBJ_DIR)/%.o)

all: build $(APP_DIR)/$(TARGET)

$(OBJ_DIR)/%.o: %.cpp
	@mkdir -p $(@D)
	$(CXX) $(CXXFLAGS) -c $< -o $@

$(APP_DIR)/$(TARGET): $(OBJECTS)
	@mkdir -p $(@D)
	$(CXX) $(CXXFLAGS) $(OBJECTS) -o $@

.PHONY: all build clean debug release run

build:
	@mkdir -p $(APP_DIR)
	@mkdir -p $(OBJ_DIR)

debug: CXXFLAGS += -DDEBUG -g
debug: all

release: CXXFLAGS += -O3
release: all

clean:
	-@rm -rvf $(BUILD)

run:
	./$(APP_DIR)/$(TARGET)