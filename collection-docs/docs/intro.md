---
sidebar_position: 1
---

# Collection Docs

In this documentation you'll find information regarding the technical and business aspects of our software.

## What is "Collection"?

Collection is a software that allows users to manage and track their various types of collections in a single place.
Currently, the following types of collections are supported:

- Trading Card Games (Pokemon, Magic: The Gathering, Flesh & Blood)
- Books and Comics/Manga
- Vinyls
- Games

## Development Philosophy

Collection is a free and open-source software which can be run locally or hosted in an application server or cloud provider.
Our goal is to create a software that provides the user the peace of mind that their data will not be lost if some external SaaS reaches their end of life.
To accomplish that, Collection will support exporting and importing collection data in multiple famous formats such as JSON, XML and YAML, along with the option to schedule database backups or local file system.

## System Design

See [this article](./business/) for business design decisions and [this article](./tech/) for technical design decisions.